<template>
  <div>
    <table class="m-auto width-full">
      <thead>
      <tr class="border-bottom color-bg-accent">
        <th
            v-for="(h, idx) in header"
            :class="idx !== header.length ? 'border-right' : ''"
            :key="`header_${idx}`"
        >
          {{ h.name }}
        </th>
      </tr>
      </thead>
      <tbody>
      <tr
          class="border-bottom"
          v-for="(item, idx) in items"
          :key="`item_${idx}`"
      >
        <td class="width-fit border-right" v-for="h in header" :key="h.key">
          {{ item[h.key] }}
        </td>
      </tr>
      </tbody>
    </table>
  </div>
  <div>
    <dh-pagination v-model:current="current" v-model:per-page="perPage" v-model:total="total"/>
  </div>
</template>

<script lang="ts">
import {defineComponent, PropType} from 'vue';
import DhPagination from "./DhPagination.vue";

type UserData = { id: number, loginId: string, userId: string, accountNonExpired: boolean, accountNonLocked: boolean }

export default defineComponent({
  name: 'DhTable',
  components: {DhPagination},
  props: {
    current: {
      type: Number,
      required: true
    },
    total: {
      type: Number,
      required: true
    },
    perPage: {
      type: Number,
      required: false,
      default: 10
    },
    items: {
      type: Array as PropType<UserData[]>,
      required: true,
    },
  },
  setup() {
    const header = [
      {name: '#', key: 'id'},
      {name: 'ログインID', key: 'loginId'},
      {name: 'ユーザID', key: 'userId'},
      {name: '有効', key: 'accountNonExpired'},
      {name: 'ロック', key: 'accountNonLocked'},
    ];
    return {header};
  },
});
</script>

<style scoped></style>
