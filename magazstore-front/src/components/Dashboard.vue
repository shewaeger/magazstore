<template>
    <div>
        <v-sparkline
                :value="value"
                :smooth="radius || false"
                :padding="padding"
                :line-width="lineWidth"
                :stroke-linecap="lineCap"
                :gradient-direction="gradientDirection"
                :fill="fill"
                :type="type"
                :auto-line-width="autoLineWidth"
                auto-draw
                :show-labels="showLabels"
                :label-size="labelSize"
        ></v-sparkline>
    </div>
</template>

<script>
    export default {
        data: () => ({
            showLabels: false,
            lineWidth: 2,
            labelSize: 7,
            radius: 10,
            padding: 8,
            lineCap: 'round',
            value: [0, 2, 5, 9, 5, 10, 3, 5, -4, -10, 1, 8, 2, 9, 0],
            gradientDirection: 'top',
            fill: false,
            type: 'trend',
            autoLineWidth: false,
        }),
    }
</script>

<style scoped>

</style>