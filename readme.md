# 使い方

## 1. とりあえずローカル起動

1. ローカルDBの準備
2. `./gradlew npmCi` (npm ci)
3. `./gradlew webpackDev` (npm webpack --watch / Vueファイルのビルドと配置)
4. `./gradlew bootRun` (SpringBootの起動)

※ ./gradlewを使うとgradleをローカルインストールする必要なしです。

---

## 2. 使ってるもの(詳細はbuild.gradle/package.json等参照)

FW: SpringBoot2(Kotlin), SpringSecurity  
Viewテンプレート: Thymeleaf3  
ORM: MyBatis  
DB: MySQL(※たまたま入ってたのが5.7)  
DBマイグレ: Flyway  
ビルド: gradle  
jsフロント： Vue3(CompositionApi) / axios CSSフレームワーク：Primer

---

## 3. プロジェクトの構成

### 3.1 フロント

#### src/main/client

フロントにVue埋め込みたい場合はここで編集してください。 入れ込めばwebpackでよしなに`src/main/resources/static`にコピーされるはず。。。

- css
    - sassでも行けるはず。。。

- js
    - common(共通処理入れようかな。。。)
    - components
    - views
        - thymeleafに対応した命名をするようにして使うコンポーネントを読み込むなど

### 3.2 バックエンド

#### src/main/kotlin/api

axios経由でリクエスト使う時とかのAPI用のcontroller/request/responseディレクトリ。  
ページネーションの切り替えとかで使うと良いのかな。。。(悩み中)

#### src/main/kotlin/web

画面返すのと初期データ(+POSTによるデータ更新)用のcontroller/request/responseディレクトリ。

#### src/main/kotlin/config

Spring周りの設定やアプリケーション全体に関わる設定を詰め込むところ。  
Thymeleaf用のBean定義とかSpringSecurityの設定等。

#### src/main/kotlin/dao

MyBatisからの結果クラス/DBのモデルを定義する

#### src/main/kotlin/mapper

MyBatisでのクエリ定義  
xml切り出しよりも〇〇Providerで定義した方がmybatisの文法覚えなくても良さそうなのでそうしようかと悩み中

#### src/main/kotlin/service

Controllerからロジック部分を切り出したクラスを入れる。 汎用性なくてロジック短ければControllerにベタでも良いかも。

#### src/main/kotlin/utils

Springの設定(Bean定義とか)とはちょっと違う共通的に使うものを入れるところ。  
configと迷いそうだけど一旦utilsとして定義

---

## 補足

### npm周り

Vue使ってフロント書く場合は 通常のnpm使うとPCローカルのnpm使うのでgradle経由で行ったほうが良さそうです。  
gradleのnode設定でローカルに対象バージョンのnodeとnpmのセットが落ちてくるとのこと。  
3.1.1の場合：node->14.15.4, npm->6.14.10

- npm install
    - `./gradlew npmInstall`
- npm ci
    - `./gradlew npmCi`
- webpackビルド(watchで差分ビルド)
    - `./gradlew webpackDev`


