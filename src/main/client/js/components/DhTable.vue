<template>
  <div>
    <table class="m-auto width-full">
      <thead>
      <tr class="border-bottom color-bg-accent">

        <th v-for="(h, idx) in header" :class="idx !== header.length ? 'border-right' : ''" :key="`header_${idx}`">
          {{ h.name }}
        </th>
      </tr>
      </thead>
      <tbody>
      <tr class="border-bottom" v-for="(item, idx)  in items" :key="`item_${idx}`">
        <td class="width-fit border-right" v-for="h in header" :key="h.key">{{ item[h.key] }}</td>
      </tr>
      </tbody>
    </table>
  </div>
  <div>
    <nav class="paginate-container" aria-label="Pagination">
      <div class="pagination">
        <span class="previous_page" aria-disabled="true">Previous</span>
        <em aria-current="page">1</em>
        <a href="#" aria-label="Page 2">2</a>
        <a href="#" aria-label="Page 3">3</a>
        <span class="gap">…</span>
        <a href="#" aria-label="Page 8">8</a>
        <a href="#" aria-label="Page 9">9</a>
        <a href="#" aria-label="Page 10">10</a>
        <a class="next_page" rel="next" href="#" aria-label="Next Page">Next</a>
      </div>
    </nav>
  </div>

</template>

<script lang="ts">
import {defineComponent} from "vue";

export default defineComponent({
  name: "DhTable",
  props: {
    // TODO: レスポンスの型をSpring側で生成するようにしたい
    items: {
      type: Array,
      required: true,
    }
  },
  setup(props) {
    const header = [
      {name: '#', key: 'id'},
      {name: 'ログインID', key: 'loginId'},
      {name: "ユーザID", key: 'userId'},
      {name: "有効", key: 'accountNonExpired'},
      {name: "ロック", key: 'accountNonLocked'},
    ]
    return {header}
  }
})
</script>

<style scoped>

</style>