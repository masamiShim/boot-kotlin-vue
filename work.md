やったこととかのメモ書き

## Node/Vueまわり

[参考にしたとこ](https://qiita.com/h1romas4/items/8345e313b351c992df08)

### Typescript + Vue3対応

#### 1. shims-vue.d.ts(IDEエラー対策)

```typescript
declare module '*.vue' {
  // createAppでのエラー対策
  // https://stackoverflow.com/questions/62216862/error-in-vue3-argument-of-type-typeof-import-dist-vue-is-not-assignabl
  import {defineComponent} from "vue";
  const component: ReturnType<typeof defineComponent>;
  export default component;
}
```

#### 2. ページ毎の読み込み

```typescript
  createApp({
  componets: Array<VueComponent>
}).mount('#el')
```

### npm install

※ nodeのバージョンとか合ってる場合は`npm install`でもOK

#### 1. 以下のコマンドで実行

- `./gradlew npmInstall`
- `./gradlew webpackDev`
    - clientディレクトリ以下のソースをjs/css/imagesにいい感じにコピー
    - 実行状態で66%で止まるけどvue/tsのへ変更後にブラウザリロードすれば良さげ

---

### MyBatis

- sqlどうしよう問題
    - クラス定義で行けるらしいからこれにしようかな。。。
        - [〇〇Provider使うやつの参考](https://su-kun1899.hatenablog.com/entry/2018/07/27/210000)

--- 

### Thymeleaf

[チートシート](https://qiita.com/oh_yeah_sayryo/items/913646e31bd2064ba5c9#java%E3%83%A1%E3%82%BD%E3%83%83%E3%83%89%E3%81%AE%E5%91%BC%E3%81%B3%E5%87%BA%E3%81%97)

- ThymeleafからVueに渡すときにクラスとかを渡すと変になるのでtoJsonとかをViewConfigに定義した
    - Bean定義しておけばThymeleafで直接読み込み可能
    - [参考](https://qiita.com/alpha_pz/items/f57baa99bb5ae0d52d0b)
