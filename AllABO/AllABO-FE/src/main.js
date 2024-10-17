import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import './css/tailwind.css'
import router from './router/router'

import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { library } from '@fortawesome/fontawesome-svg-core'
import { faHeart as faHeart1 } from '@fortawesome/free-solid-svg-icons'
import { faHeart as faHeart2 } from '@fortawesome/free-regular-svg-icons'
import { faHeart as faHeart3 } from '@fortawesome/free-regular-svg-icons/faHeart'
import { faHeart as faHeart4 } from '@fortawesome/free-solid-svg-icons/faHeart'
// import {heart}
import { faQuoteLeft } from '@fortawesome/free-solid-svg-icons'
import { faQuoteRight } from '@fortawesome/free-solid-svg-icons'
import { faChevronDown } from '@fortawesome/free-solid-svg-icons/faChevronDown' 
import { faChevronUp } from '@fortawesome/free-solid-svg-icons/faChevronUp'

library.add(faHeart1, faHeart2, faHeart3, faHeart4 ,faQuoteLeft,faQuoteRight, faChevronDown, faChevronUp)

const app=createApp(App)
const pinia = createPinia()

app.component('font-awesome-icon', FontAwesomeIcon)

app.use(pinia)
app.use(router)
app.mount('#app')
// createApp(App).mount('#app')
