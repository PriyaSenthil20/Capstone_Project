<template>
  <div class="home">
    <nav>
      <nav-options/>
      
    </nav>
    <div>
      <welcome/>
    </div>
    <div>
    </div>
  </div>
</template>

<script>
import NavOptions from '../components/NavOptions.vue';
import Welcome from '../components/Welcome.vue';
import Specials from '../components/Specials.vue';
import AboutUs from '../components/AboutUs.vue';

export default {
  components: {
    NavOptions,
    Welcome,
    Specials,
    AboutUs
  }
};
</script>