<template>
    <div class="text-center ma-12" :v-show="progressShowed">
        <v-progress-circular
                :indeterminate="true"
                :rotate="0"
                :size="27"
                :width="2"
                color="light-blue"
        ></v-progress-circular>
    </div>
</template>

<script>
    export default {
        created() {
            this.$axios.get('http://localhost:8080/api/category/list?size=20')
            .then((data) => {
                console.log(data);
                this.progressShowed = false;
            })
        },
        data: () => ({
            progressShowed: true,
        })
    }
</script>

<style scoped>

</style>