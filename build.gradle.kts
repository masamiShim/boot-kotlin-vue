import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.flywaydb.flyway") version "7.7.3"
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31"
    // MyBatis用
    id("org.jetbrains.kotlin.plugin.noarg") version "1.5.31"
    id("idea")
    // プロジェクトローカルにNode突っ込んでくれるやつ
    id("com.github.node-gradle.node") version "3.1.1"
}
group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
    uri("https://plugins.gradle.org/m2/")
}

// gradleTaskに必要(FlyWay使う必要があれば)
flyway {
    url = "jdbc:mysql://127.0.0.1:44060/nodean"
    user = "node"
    password = "nodean"
}

// MyBatis用で「data class」使う際に引数なしコンストラクタの作成をやってくれるやつ
noArg {
    annotation("com.example.kotlin_template.utils.NoArg")
}

node {
    node.version.set("16.13.0")
    npmVersion.set("8.1.0")
    // グローバルじゃなくてローカルにダウンロードして使うように設定
    download.set(true)
}

// devtools用設定(IntellijIdea)
idea {
    module {
        inheritOutputDirs = false
        outputDir = file("$buildDir/classes/kotlin/main")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security:2.5.6")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf:2.5.6")
    implementation("org.springframework.boot:spring-boot-starter-web:2.5.6")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
    implementation("org.flywaydb:flyway-core:8.0.2")
    // mysql
    implementation("mysql:mysql-connector-java:8.0.26")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.0")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5:3.0.4.RELEASE")
    developmentOnly("org.springframework.boot:spring-boot-devtools:2.5.5")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.5.6")
    testImplementation("org.springframework.security:spring-security-test:5.5.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "16"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
// リソースの即時反映(開発用？)
tasks.getByName<org.springframework.boot.gradle.tasks.run.BootRun>("bootRun") {
    sourceResources(sourceSets["main"])
}

tasks.register<com.github.gradle.node.npm.task.NpmTask>("npmCi") {
    dependsOn(tasks.npmSetup)
    npmCommand.set(listOf("ci"))
    inputs.files("package.json", "package-lock.json")
    outputs.dir("node_modules")
}

tasks.register<com.github.gradle.node.npm.task.NpmTask>("npmLint") {
    args.set(listOf("run", "lint"))
}

tasks.register<com.github.gradle.node.npm.task.NpmTask>("webpackProd") {
    dependsOn(tasks.getByName("npmCi"))
    dependsOn(tasks.getByName("npmLint"))
    environment.set(mapOf("NODE_ENV" to "development"))
    args.set(listOf("run", "webpack:prod"))
}

tasks.register<com.github.gradle.node.npm.task.NpmTask>("webpackDev") {
    dependsOn(tasks.getByName("npmLint"))
    environment.set(mapOf("NODE_ENV" to "development"))
    args.set(listOf("run", "webpack:dev"))
}
