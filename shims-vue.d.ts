declare module '*.vue' {
  // createAppでのエラー対策
  // https://stackoverflow.com/questions/62216862/error-in-vue3-argument-of-type-typeof-import-dist-vue-is-not-assignabl
  import { defineComponent } from 'vue';
  const component: ReturnType<typeof defineComponent>;
  export default component;
}