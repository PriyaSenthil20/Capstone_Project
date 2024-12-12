<template>
  <div class="menu-view">
    <nav-options />
    <div class="menu-header">
      <h1>Our Menu</h1>
      <p>Explore our delicious offerings crafted with love and the finest ingredients.</p>
    </div>
    <div class="menu-gallery">
      <div
        v-for="item in menuItems"
        :key="item.id"
        class="menu-item"
        @click="navigateToOrder(item)"
      >
        <img :src="item.image" :alt="item.name" class="menu-image" />
        <h2>{{ item.name }}</h2>
        <p>{{ item.description }}</p>
        <p><strong>Price:</strong> ${{ item.price }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import NavOptions from "../components/NavOptions.vue";

export default {
  components: {
    NavOptions,
  },
  data() {
    return {
      menuItems: [
        {
          id: 1,
          name: "Pepperoni Pizza",
          description: "A delicious classic topped with pepperoni and mozzarella cheese.",
          price: "12.99",
          image: new URL("../assets/WholePepperoni.jpg", import.meta.url).href,
        },
        {
          id: 2,
          name: "Cheese Pizza",
          description: "Rich, gooey, and cheesy – a pizza lover's dream.",
          price: "10.99",
          image: new URL("../assets/WholeCheese.jpg", import.meta.url).href,
        },
        {
          id: 3,
          name: "Apollo Special",
          description: "A supreme blend of toppings for a truly satisfying bite.",
          price: "15.99",
          image: new URL("../assets/Apollo.jpg", import.meta.url).href,
        },
        {
          id: 4,
          name: "Lei'Ed",
          description: "A masterful selection for a pizza king.",
          price: "15.99",
          image: new URL("../assets/Leied.jpg", import.meta.url).href,
        },
        {
          id: 5,
          name: "The Master",
          description: "Can you handle the Master?",
          price: "15.99",
          image: new URL("../assets/master.jpg", import.meta.url).href,
        },
        {
          id: 5,
          name: "Due South",
          description: "Can you handle the Master?",
          price: "15.99",
          image: new URL("../assets/dueSouth.jpg", import.meta.url).href,
        },
        {
          id: 5,
          name: "RockyBalboa",
          description: "Can you handle the Master?",
          price: "15.99",
          image: new URL("../assets/master.jpg", import.meta.url).href,
        },
        {
          id: 5,
          name: "The Versace",
          description: "Can you handle the Master?",
          price: "15.99",
          image: new URL("../assets/Versace.jpg", import.meta.url).href,
        }
      ],
    };
  },
  methods: {
    navigateToOrder(item) {
      this.$router.push({ name: "customerOrder", query: { menuItem: JSON.stringify(item) } });
    },
  },
};
</script>

<style scoped>
.menu-view {
  font-family: "Arial", sans-serif;
  padding: 20px;
  text-align: center;
  min-height: 100vh;
}

.menu-header {
  margin-bottom: 30px;
}

.menu-header h1 {
  font-size: 2.5rem;
  color: #d84d22;
  margin-bottom: 10px;
}

.menu-header p {
  font-size: 1.2rem;
  color: #555;
}

.menu-gallery {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 30px;
}

.menu-item {
  width: 300px;
  padding: 20px;
  background: linear-gradient(to bottom, #ffffff, #fdf3e4);

  border-radius: 12px;
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
  text-align: center;
  cursor: pointer;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.menu-item:hover {
  transform: scale(1.05);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

.menu-image {
  width: 100%;
  height: auto;
  border-radius: 8px;
  margin-bottom: 15px;
}

.menu-item h2 {
  font-size: 1.5rem;
  color: #2d3436;
  margin-bottom: 10px;
}

.menu-item p {
  font-size: 1rem;
  color: #555;
}

/* Section Headers */
.menu-view h1 {
  font-size: 2rem;
  color: #d84d22;
  margin-bottom: 20px;
}
</style>
