<template>
  <div class="sample-text sample-color">SampleComponent: {{ hello }}</div>
</template>
<script lang="ts">
import { defineComponent, ref } from 'vue';

export default defineComponent({
  name: 'SampleComponent',
  setup() {
    const hello = ref('hello');
    return { hello };
  },
});
</script>
<style></style>
