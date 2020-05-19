import lodash from 'lodash'

export default {
    install: function(Vue, name = '_'){
        lodash.mapAndFilter = function(collection, filter, map, acc){
            return lodash.reduce(collection, function(acc, value, i) {
                if(!filter(value, i)){
                    return acc;
                }
                acc.push(map(value, i));
                return acc;
            }, acc);
        }
        Object.defineProperty(Vue.prototype, name, {value: lodash});
    }
}