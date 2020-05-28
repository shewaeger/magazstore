<template>
    <div>
<!--        <div class="text-center ma-12" v-show="progressShowed">-->
<!--            <v-progress-circular-->
<!--                    :indeterminate="true"-->
<!--                    :rotate="0"-->
<!--                    :size="27"-->
<!--                    :width="2"-->
<!--                    color="light-blue"-->
<!--            ></v-progress-circular>-->
<!--        </div>-->
<!--        <div v-show="!progressShowed">-->
<!--            -->
<!--        </div>-->
        <v-treeview
                :items="items"
                :open.sync="open"
                :load-children="fetchCategories"
                open-on-click
                transition
        >

        </v-treeview>

    </div>
</template>

<script>
    export default {
        created() {

        },
        data: () => ({
            progressShowed: true,
            open: [],
            categories: [],
        }),
        computed: {
            items () {
                return [
                    {
                        name: 'Категории',
                        children: this.categories,
                    },
                ]
            },
        },
        methods: {
            //eslint-disable-next-line
            async fetchCategories(item){

                return this.$axios.get('http://localhost:8080/api/category/list?size=20')
                    .then((data) => {
                        this.categories.push(...data.data.content);
                    })
            }
        }
    }
</script>

<style scoped>

</style>