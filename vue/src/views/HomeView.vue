<template>
  <div class="home">
    <nav class="navbar">
      <nav-options />
    </nav>
    <div class="welcome-section">
      <welcome />
    </div>
    
  </div>
  <div class="gallery-body">
    
    <div class="gallery-container">
        <div class="gallery">
            <div class="gallery-inner">
                <div v-for="(photo, index) in photoGallery" :key="index" class="photo-item ">
                <img :src="photo.image" :alt="photo.description" class="gallery-image" />
                <p>{{ photo.description }}</p>
                </div>
            </div>
        </div>
    </div>
  </div>
</template>

<script>
import NavOptions from '../components/NavOptions.vue';
import Welcome from '../components/Welcome.vue';
import Specials from '../components/Specials.vue';

import PizzaService from '../services/PizzaService.js';


export default {
  components: {
    NavOptions,
    Welcome,
    
  },
  data() {
    return{ 
      photoGallery: [
        {
          image: new URL("../assets/ModelA.jpg", import.meta.url).href,
          description: "Pizza ready to serve.",
        },
      
        {
          image: new URL("../assets/ModelD.jpg", import.meta.url).href,
          description: "We LOVE Pizza!",
        },
        {
          image: new URL("../assets/ModelB.jpg", import.meta.url).href,
          description: "Fresh ingredients on display.",
        }, {
          image: new URL("../assets/pic7.jpg", import.meta.url).href,
          description: "Bringing Smiles One Slice at a Time.",
        },
        {
          image: new URL("../assets/ModelC.jpg", import.meta.url).href,
          description: "In Pizza We Crust!",
        }, {
          image: new URL("../assets/pic1.jpg", import.meta.url).href,
          description: "Where Every Slice Feels Like Home.",
        }, {
          image: new URL("../assets/pic5.jpg", import.meta.url).href,
          description: "Your Pizza, Your Way!",
        }, {
          image: new URL("../assets/pic2.jpg", import.meta.url).href,
          description: "Life Happens, Pizza Helps.",
        }, {
          image: new URL("../assets/pic3.jpg", import.meta.url).href,
          description: "Cheese Your Day with Us!",
        }, {
          image: new URL("../assets/ModelC.jpg", import.meta.url).href,
          description: "Crust to Crave, Flavor to Savor",
        },
        {
          image: new URL("../assets/actionPic.jpg", import.meta.url).href,
          description: "Now that's taking one for the team.",
        },
        {
          image: new URL("../assets/inCar.jpg", import.meta.url).href,
          description: "The pizza didn't even make it home.",
        },
        {
          image: new URL("../assets/Pepperoni2.jpg", import.meta.url).href,
          description: "The pizza so big, it does not fit the frame.",
        },
        {
          image: new URL("../assets/heart.jpg", import.meta.url).href,
          description: "We LOVE Pizza!",
        },
        {
          image: new URL("../assets/grandmas.jpg", import.meta.url).href,
          description: "Hot Pizza, Cool Vibes.",
        },
        {
          image: new URL("../assets/WholeCheese.jpg", import.meta.url).href,
          description: "The Secret Ingredient is Always Pizza!",
        },
        {
          image: new URL("../assets/WholePepperoni.jpg", import.meta.url).href,
          description: "Fuel Your Day, One Slice at a Time.",
        }
        
        
      ],
      products: []
    };
  },
  methods: {
      /*getMenu(){
      
      PizzaService.menu()
      .then(response => {
        
        this.products = response.data;

        for(let i = 0; i < products.length; i++){
          for(let j = 0; j < products.length; i++){
            if(products[i].productName == products[j].productName){
              products.splice(j, 1);
            }
          }
        }

        console.log(response.data);
      })
      .catch(error => {
        console.log(error);
      })
    } */
  },
  created() {
   // this.getMenu();
  }
};
</script>

<style scoped>
.home {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;

  font-family: Arial, sans-serif;
  
  /*
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #f4f4f4;
  font-family: Arial, sans-serif;
  */
}

.navbar {
  width: 100%;
  display: bottom;
  justify-content: center;
  /*
  background-color: #e63946;
  padding: 1rem 0;
  display: flex;
  justify-content: center;
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
  */
}

.welcome-section {
  width: 90%;
  max-width: 1200px;
  padding: 2rem 0;
  background-color: white;
  border-radius: 10px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
  margin-bottom: 2rem;
  display: flex;
  justify-content: center;
  align-items: center;
}

.content-section {
  width: 90%;
  max-width: 1200px;
  display: flex;
  flex-direction: column;
  gap: 2rem;
}

.content-section > div {
  background-color: white;
  border-radius: 10px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
  padding: 1.5rem;
}



.gallery-container {
    width: 100%;
    overflow: hidden;
    padding: 20px 0;
}

.gallery {
    display: flex;
    width: max-content;
    animation: scrollGallery 40s linear infinite;
}

.gallery-inner {
    display: flex;
}

.gallery img {
    max-width: 300px;
    height: auto;
    margin-right: 20px;
    box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
}

@keyframes scrollGallery {
    0% {
        transform: translateX(0);
    }
    100% {
        transform: translateX(-30%); 
    }
}
</style>
