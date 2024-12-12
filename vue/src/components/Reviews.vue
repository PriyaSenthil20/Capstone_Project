<template>
  <div class="reviews-container">
    <div class="reviews-header">
      <h2>Pizza Talk</h2>
      <p>See what our customers are saying about us!</p>
    </div>
    <div class="reviews">
      <div class="review-card" v-for="comment in comments" :key="comment.id">
        <div class="review-header">
          <h3 class="author">{{ comment.author }}</h3>
          <p class="postedOn">{{ new Date(comment.postedOn).toDateString() }}</p>
        </div>
        <p class="review-body">{{ comment.body }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      comments: [
        {
          author: "Zach",
          postedOn: "Dec-10-2024",
          body: "Rocco's Pizza is hands-down the best pizza spot in town! The crust is perfectly crisp, the sauce is flavorful, and the cheese is always melted to perfection.",
        },
        {
          author: "Patrick",
          postedOn: "Nov-25-2024",
          body: "The flavors are authentic, and you can tell they take pride in their craft.",
        },
        {
          author: "Henry",
          postedOn: "Nov-10-2024",
          body: "Rocco's is not just a pizza place—it’s a vibe.",
        },
      ],
    };
  },
};
</script>

<style scoped>
.reviews-container {
  font-family: "Arial", sans-serif;
  background-color: #f9f9f9;
  padding: 40px 20px;
  border-radius: 12px;
  box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
  max-width: 900px;
  margin: 40px auto;
  text-align: center;
}

.reviews-header {
  margin-bottom: 30px;
}

.reviews-header h2 {
  font-size: 2.5rem;
  color: #d84d22;
  margin-bottom: 10px;
}

.reviews-header p {
  font-size: 1.2rem;
  color: #555;
}

.reviews {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.review-card {
  background-color: #ffffff;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  padding: 20px;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  text-align: left;
}

.review-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 6px 15px rgba(0, 0, 0, 0.2);
}

.review-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
}

.author {
  font-size: 1.3rem;
  font-weight: bold;
  color: #457b9d;
}

.postedOn {
  font-size: 0.9rem;
  color: #aaa;
  font-style: italic;
}

.review-body {
  font-size: 1rem;
  line-height: 1.6;
  color: #333;
}

@media (max-width: 768px) {
  .reviews-container {
    padding: 20px 10px;
  }

  .reviews-header h2 {
    font-size: 2rem;
  }

  .reviews-header p {
    font-size: 1rem;
  }

  .review-card {
    padding: 15px;
  }

  .author {
    font-size: 1.1rem;
  }

  .review-body {
    font-size: 0.9rem;
  }
}
</style>
