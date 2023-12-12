import Home from "../Pages/Home.jsx";
import Login from "../Pages/Login.jsx";
import ForgetPassword from "../Pages/ForgetPassword.jsx";

const routes = [
    //Navigate page
    {path: '/', component: Home},
    {path: '/login', component: Login},
    {path: '/forget-password', component: ForgetPassword},
]

export default routes;