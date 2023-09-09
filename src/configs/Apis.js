import axios from "axios";
import cookie from "react-cookies";

const SERVER_CONTEXT ="/ReviewFood"
const SERVER = "http://localhost:8080";

export const endpoints ={
    "categories": `${SERVER_CONTEXT}/api/categoryRes/`,
    "cities": `${SERVER_CONTEXT}/api/city/`,
    "restaurants": `${SERVER_CONTEXT}/api/restaurant/`,
    "login": `${SERVER_CONTEXT}/api/login/`,
    "current-user": `${SERVER_CONTEXT}/api/current-user/`,
    "register": `${SERVER_CONTEXT}/api/users/`,
    "pay": `${SERVER_CONTEXT}/api/pay/`,
    "details": (restaurantId) => `${SERVER_CONTEXT}/api/restaurant/${restaurantId}/`,
    "comments": (restaurantId) => `${SERVER_CONTEXT}/api/restaurant/${restaurantId}/comments/`,
    "add-comment": `${SERVER_CONTEXT}/api/comments/`,
    "menu": (restaurantId) => `${SERVER_CONTEXT}/api/restaurant/${restaurantId}/menu/`,
    "menu-item": (menuId) => `${SERVER_CONTEXT}/api/menu/${menuId}/`,
    "create-restaurant": `${SERVER_CONTEXT}/api/restaurant/create/`,
    "follow": `${SERVER_CONTEXT}/api/follow/`,
    "check-follow":(userId, restaurantId) => `${SERVER_CONTEXT}/api/check-follow?usserId=${userId}&restaurantId=${restaurantId}`,
}

export const authApi = () => {
    return axios.create({
        baseURL: SERVER,
        headers: {
            "Authorization":  cookie.load("token")
        }
    })
}

export default axios.create({
    baseURL: SERVER
})