//import Page404 from './components/ErrorPage/Page404';
import LoadingPage from "./components/LoadingPage";
//import MaintenancePage from 'components/MaintenancePage';
//import LayoutCourse from './pages/Course/LayoutCourse';
import React, { Suspense, lazy, useEffect, useState } from "react";
import { Route, Routes } from "react-router-dom";
import Dashboard from "./pages/Dashboard"

import {
  DASHBOARD_STUDENT,
  DASHBOARD_TEACHER,
} from "./constraints/StudentDashboard";
import { useSelector } from "react-redux";
//import SearchCourse from 'pages/SearchCourse';
//import SellCourse from 'pages/SellCourse';
const Home = lazy(() => import("./pages/Home"));

const Login = lazy(() => import("./components/Login"));
const Register = lazy(() => import("./components/Register"));
const Profile = lazy(() => import("./pages/Dashboard/Profile"));

const makeLoading = (component) => <Suspense fallback={<LoadingPage />}>{component}</Suspense>

const TEACHER = [
  {
    path: "profile",
    component: Profile,
  },
];

const STUDENT = [
  {
    path: "profile",
    component: Profile,
  },
];

function ConfigRoute() {
  const role = useSelector(state => state.setting.role) || 'student'
  const [dashboardComponents, setDashboardComponents] = useState(role === 'student' ? STUDENT : TEACHER)
  //const [courseComponents, setCourseComponents] = useState(role === 'student' ? COURSE_STUDENT : COURSE_TEACHER)
  const [sidebarTab, setSidebarTab] = useState(role === 'student' ? DASHBOARD_STUDENT : DASHBOARD_TEACHER)
  useEffect(() => {
    if (role === 'teacher') {
      setDashboardComponents(TEACHER)
      //setCourseComponents(COURSE_TEACHER)
      setSidebarTab(DASHBOARD_TEACHER)
    }
    else {
      setDashboardComponents(STUDENT)
      //setCourseComponents(COURSE_STUDENT)
      setSidebarTab(DASHBOARD_STUDENT)
    }

  }, [role])

  return (
    <Suspense fallback={<LoadingPage />}>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="my" element={<Dashboard sidebarTab={sidebarTab} />}>
          {dashboardComponents.map((item) => (
            <Route
              key={item.path}
              path={item.path}
              element={makeLoading(<item.component />)}
            />
          ))}
        </Route>
      </Routes>
    </Suspense>
  );
}

export default ConfigRoute;
