import React, { Fragment } from 'react';
import './index.scss';
import { Outlet, Route, RouterProvider, createBrowserRouter, createRoutesFromElements } from 'react-router-dom';
import { createRoot } from 'react-dom/client';
import RegistrationForm from './routes/register/RegistrationForm';
import Login from "./routes/login/Login";
import MainLayout from "./global/MainLayout"
import PageNotFound from "./global/PageNotFound"
import DashBoard from "./routes/app/DashBoard";
import { UserAuthProvider } from './global/UserAuthProvider';
import LandingPage from './global/LandingPage';

document.title = "Pandey ji's assignment";


document.title = "Pandey Dance Academy";
const routesWithJSX = createBrowserRouter(
    createRoutesFromElements(
        <Route path="" element={<UserAuthProvider><MainLayout /></UserAuthProvider>} >
            <Route path="" element={<LandingPage />} />
            <Route path="register" element={<RegistrationForm />} />
            <Route path="login" element={<Login />} />
            <Route path="app" element={<Outlet />} >
                <Route path="" element={<DashBoard />}>
                    <Route path='dashboard' element={<div>Dashboard</div>} />
                </Route>
            </Route>
            <Route path='*' element={<PageNotFound />} />
        </Route>
    ),
);

const root = createRoot(document.getElementById('root'));
root.render(
    <Fragment>
        {/* <Provider store={reduxStore}> */}
        <RouterProvider router={routesWithJSX} />
        {/* </Provider> */}
    </Fragment>
);

