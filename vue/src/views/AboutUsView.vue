<template>
    <div>
        <nav-options />
        </div>

      <div class="aboutUs">
      <about-us />
      </div>
      <div class="ReviewsComponent">
        <reviews/>
        </div>
   
</template>

<script>


import NavOptions from "../components/NavOptions.vue";
import AboutUs from "../components/AboutUs.vue";
import Reviews from "../components/Reviews.vue";

export default {

 
components: {
    AboutUs,
    NavOptions,
    Reviews
}

}

</script>

