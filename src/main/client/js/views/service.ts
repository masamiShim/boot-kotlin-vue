import '../common/common.ts';
import SampleComponent from '@@/SampleComponent.vue';
import DhTable from '@@/datatable/DhTable.vue';
import { createApp } from 'vue';

createApp({
  components: {
    SampleComponent,
    DhTable,
  },
}).mount('#sample');
