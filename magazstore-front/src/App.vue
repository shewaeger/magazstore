<template>
    <v-app>
        <v-navigation-drawer app v-model="drawer">
            <v-list-item>
                <v-list-item-content>
                    <v-list-item-title class="title">
                        <v-list-item-icon>
                            <v-icon>mdi-account</v-icon>
                        </v-list-item-icon>
                        ShewaEger
                    </v-list-item-title>
                    <v-list-item-subtitle>
                        Панель администратора
                    </v-list-item-subtitle>
                </v-list-item-content>
            </v-list-item>

            <v-divider></v-divider>

            <v-list
                    dense
                    nav
            >
                <v-list-item v-for="item in items" :key="item.title" :to="item.route">
                    <v-list-item-icon>
                        <v-icon>{{ item.icon }}</v-icon>
                    </v-list-item-icon>

                    <v-list-item-content>
                        <v-list-item-title>{{ item.title }}</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
        </v-navigation-drawer>
        <v-app-bar app dense>
            <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>
        </v-app-bar>
        <v-content>
            <router-view></router-view>
        </v-content>
    </v-app>
</template>

<script>
    export default {
        data: () => ({
            drawer: false,
        }),
        computed: {
            items: function () {
                return this._.mapAndFilter(this.$router.options.routes,
                    (value) => value.meta.showInMenu,
                    (value) => ({
                        icon: value.meta.icon,
                        route: value.path,
                        title: value.meta.header
                    }),
                    []);
            },
        },
        methods: {}
    }
</script>