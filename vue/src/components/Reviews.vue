<template>
    <div class="comments">
      <h2>Pizza Talk </h2>
      <div class="comment" v-for="comment in comments" v-bind:key="comment.id">
        <h3>{{ comment.author }}</h3>
        <p class="postedOn">{{ new Date(comment.postedOn).toDateString() }}</p>
        <p>{{ comment.body }}</p>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    
    data(){
        return {
            comments:[{
                author:'Zach',
                postedOn:'Dec-10-2024',
                body:'Rocco`s Pizza is hands-down the best pizza spot in town! The crust is perfectly crisp, the sauce is flavorful, and the cheese is always melted to perfection.'
            },
            {
                author:'Patrick',
                postedOn:'Nov-25-2024',
                body:'The flavors are authentic, and you can tell they take pride in their craft. '
            },
            {
                author:'Henry',
                postedOn:'Nov-10-2024',
                body:'Rocco`s is not just a pizza place—it`s a vibe. '
            }]
        };
    }
  };
  </script>
  
  
  <style scoped>
.comments {
  background-color: #f5f7fa; /* Light gray with a subtle blue hue */
  border-radius: 12px;
  padding: 20px 30px;
  max-width: 800px;
  margin: 20px auto;
  box-shadow: 0px 6px 15px rgba(15, 60, 184, 0.1); /* Softer, elevated shadow */
  font-family: 'Roboto', Arial, sans-serif; /* Modern, readable font */
}
.comments h2 {
  color: #2d3436; /* Dark neutral for the heading */
  font-size: 2rem;
  font-weight: 700;
  border-bottom: 3px solid #1268aae3; /* Bold blue for a standout divider */
  padding-bottom: 10px;
  margin-bottom: 25px;
  text-align: center;
  
  letter-spacing: 1px; /* Adds a modern touch */
}
.comment {
  margin: 25px 0;
  padding-bottom: 15px;
  border-bottom: 1px solid #dfe6e9; /* Soft gray for separation */
}
.comment:last-child {
  border-bottom: none;
}
.comment h3 {
  color:#457b9d; /* Vibrant blue for author names */
  font-size: 1.3rem;
  font-weight: 600;
  margin-bottom: 8px;
}
.postedOn {
  font-size: 0.9rem;
  color: #636e72; /* Muted gray for timestamps */
  margin-bottom: 12px;
  font-style: italic;
  display: block; /* Better alignment and spacing */
}
.comment p {
  color: #2d3436; /* Consistent dark text for readability */
  font-size: 1rem;
  line-height: 1.7;
  margin: 5px 0;
  text-align: justify; /* Improves text alignment for long comments */
}
.comment:hover {
  background-color: #dfe6e9; /* Light highlight on hover for interactivity */
  border-radius: 8px;
  padding: 15px;
  transition: background-color 0.3s ease;
}
</style>
