<template>
  <div class="menu-page">
    <h1 class="menu-title">Our Menu</h1>
    <div class="menu-gallery">
      <div v-for="item in menuItems" :key="item.id" class="menu-item">
        <img :src="item.image" :alt="item.name" class="menu-item-image" />
        <h2>{{ item.name }}</h2>
        <p>{{ item.description }}</p>
        <p class="menu-price">${{ item.price.toFixed(2) }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  computed: {
    menuItems() {
      return this.$store.state.menuItems;
    },
  },
};
</script>

<style scoped>
.menu-page {
  padding: 20px;
  background: url("../assets/PaperBackRound.png") no-repeat center center fixed;
  background-size: cover;
}

.menu-title {
  text-align: center;
  font-size: 2.5rem;
  color: #d84d22;
  margin-bottom: 20px;
}

.menu-gallery {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 20px;
}

.menu-item {
  width: 250px;
  text-align: center;
  padding: 10px;
  background-color: white;
  border-radius: 10px;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
}

.menu-item-image {
  width: 100%;
  height: auto;
  border-radius: 10px;
}

.menu-price {
  font-size: 1.2rem;
  font-weight: bold;
  color: #4caf50;
}
</style>
