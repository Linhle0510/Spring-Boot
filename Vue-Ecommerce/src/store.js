import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    infoPage:[],
    cartItems:[],
    items: [
      {
        id:0,
        img: require('@/assets/1.jpg'),
        title: 'sofa 243',
        price: 156,
        color: 'white',
        type: 'sofa'
      }, {
        id:1,
        img: require('@/assets/2.jpg'),
        title: 'lamp 54',
        price: 756,
        color: 'yellow',
        type: 'lamp'
      }, {
        id:2,
        img: require('@/assets/3.jpg'),
        title: 'chair 34',
        price: 362,
        color: 'blue',
        type: 'chair'
      }, {
        id:3,
        img: require('@/assets/4.jpg'),
        title: 'chair 24',
        price: 505,
        color: 'black',
        type: 'chair'
      }, {
        id:4,
        img: require('@/assets/5.jpg'),
        title: 'sofa 1',
        price: 243,
        color: 'black',
        type: 'sofa'
      }, {
        id:5,
        img: require('@/assets/6.jpg'),
        title: 'chair 25',
        price: 44,
        color: 'black',
        type: 'chair'
      }, {
        id:6,
        img: require('@/assets/7.jpg'),
        title: 'chair 124',
        price: 505,
        color: 'blue',
        type: 'chair'
      }, {
        id:7,
        img: require('@/assets/8.jpg'),
        title: 'chair 64',
        price: 432,
        color: 'blue',
        type: 'chair'
      },
      {
        id:8,
        img: require('@/assets/9.jpg'),
        title: 'lamp 44',
        price: 390,
        color: 'white',
        type: 'lamp'
      },
      {
        id:9,
        img: require('@/assets/10.jpg'),
        title: 'chair 33',
        price: 756,
        color: 'yellow',
        type: 'chair'
      },
      {
        id:10,
        img: require('@/assets/11.jpg'),
        title: 'lamp 23',
        price: 44,
        color: 'black',
        type: 'lamp'
      },
      {
        id:11,
        img: require('@/assets/12.jpg'),
        title: 'chair 234',
        price: 156,
        color: 'blue',
        type: 'chair'
      },
      {
        id:12,
        img: require('@/assets/13.jpg'),
        title: 'sofa 234',
        price: 756,
        color: 'blue',
        type: 'sofa'
      },
      {
        id:13,
        img: require('@/assets/14.jpg'),
        title: 'chair 43',
        price: 756,
        color: 'black',
        type: 'chair'
      },
      {
        id:14,
        img: require('@/assets/table-1.jpg'),
        title: 'table 32',
        price: 756,
        color: 'white',
        type: 'table'
      }
    ]
  },
  // plugins: [createPersistedState()],
  getters:{
    itemsNumber(state){  // Cart Component
      return state.cartItems.length
    },
    totalPrice(state) { // Cart Component
      if (state.cartItems.length != 0){
       return state.cartItems.reduce((a, b) => {
         return (b.price == null ) ? a : a + b.price
       }, 0)
      }
    },
    infoLength(state) { // Info Component
      if(state.infoPage.length > 0) {
        return state.infoPage.splice(0, 1)
      }
    }
  },
  mutations: {
    inCart(state, n) { // Cart Component
      return state.cartItems.push(n)
    },
    outCart(state, n) { // Cart Component
      let index = state.cartItems.findIndex(x => x.id === n)
      return state.cartItems.splice(index, 1)
    },
    addtoInfo(state, n) { // Info Component
       return state.infoPage.push(n)
    }
  },
})
