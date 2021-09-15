<template>
  <div id="app">
    <Header />
    <router-view />
    <Footer />
  </div>
</template>

<script>
import Header from '@/Components/Header.vue'

import Footer from '@/Components/Footer.vue'


export default{
  name: 'app',
  components: {
    Header,  Footer
  }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css?family=Exo+2');
#app {
  background-color: #F8F8F8 !important;
  font-family: 'Exo 2', sans-serif !important;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
</style>
