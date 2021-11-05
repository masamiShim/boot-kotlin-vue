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

// 3.1.1 node 14.15.4
// 3.1.1 npm 6.14.10
node {
    // グローバルじゃなくてローカルにダウンロードして使うように設定
    download.set(true)
}

idea {
    module {
        inheritOutputDirs = false
        outputDir = file("$buildDir/classes/kotlin/main")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.flywaydb:flyway-core")
    // mysql
    implementation("mysql:mysql-connector-java:8.0.26")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.0")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
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
tasks.register<com.github.gradle.node.npm.task.NpmTask>("webpackProd") {
    dependsOn(tasks.getByName("npmCi"))
    environment.set(mapOf("NODE_ENV" to "development"))
    args.set(listOf("run", "webpack:prod"))
}
tasks.register<com.github.gradle.node.npm.task.NpmTask>("webpackDev") {
    environment.set(mapOf("NODE_ENV" to "development"))
    args.set(listOf("run", "webpack:dev"))
}
