<template>
  <div class="home">
    <nav>
      <nav-options/>
    </nav>
  </div>
  <h1>Logout</h1>
</template>

<script>
import NavOptions from '../components/NavOptions.vue';

export default {
  components: {
    NavOptions
  },
  created() {
    this.$store.commit("LOGOUT");
    this.$router.push("/login");
  }
};
</script>
