<template>
  <div class="home">
    <nav>
      <nav-options/>
    </nav>
  </div>
</template>

<script>
import NavOptions from '../components/NavOptions.vue';
import Welcome from '../components/Welcome.vue'

export default {
  components: {
    NavOptions,
    Welcome
  }
};
</script>