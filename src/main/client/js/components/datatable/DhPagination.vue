<template>
  <nav class="paginate-container" aria-label="Pagination">
    <div class="pagination">
      <a class="previous_page" rel="prev" href="#" :aria-disabled="lockPrev" @click.stop="prev">prev</a>
      <templtate v-for="page in [...Array(totalPages).keys()].map((i) => i + 1)">
        <em v-if="cur === (page)" aria-current="page">{{ page }}</em>
        <a v-else href="#" :aria-label="`Page ${page}`" @click.stop="handlePageClick(Number(page))">{{ page }}</a>
        <!--<span class="gap">…</span>-->
      </templtate>
      <a class="next_page" rel="next" href="#" aria-label="Next Page" :aria-disabled="lockNext"
         @click.stop="next">next</a>
    </div>
  </nav>
</template>

<script lang="ts">
import {computed, defineComponent} from "vue";

export default defineComponent({
  name: "DhPagination",
  props: {
    current: {
      type: Number,
      required: true
    },
    perPage: {
      type: Number,
      required: true,
    },
    total: {
      type: Number,
      required: true
    }
  },
  emits: [
    'update:current'
  ],
  setup(props, {emit}) {
    const cur = computed({
      get: () => props.current,
      set: (val) => emit('update:current', val)
    })
    const totalPages = Math.ceil(props.total / props.perPage)

    const handlePageClick = (val: number) => {
      cur.value = val
    }

    const next = () => {
      cur.value++
    }

    const prev = () => {
      cur.value--
    }

    const lockPrev = computed(() => {
      return cur.value <= 1
    })
    const lockNext = computed(() => {
      return cur.value >= totalPages
    })
    return {cur, totalPages, lockPrev, lockNext, handlePageClick, prev, next}
  }
})
</script>

<style scoped>

</style>