import axios from 'axios'

export default {
    install: function(Vue, name = '$axios'){
        Object.defineProperty(Vue.prototype, name, {value: axios});
    }
}