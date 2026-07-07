import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import Login from "./pages/Login.jsx"
import { BrowserRouter , createBrowserRouter, RouterProvider } from "react-router-dom";
import Feed from './pages/Feed.jsx'

const router = createBrowserRouter([
  {
    path: "/",
    Component: App
  },
  {
    path: "/login",
    Component: Login
  },
  {
    path: "/feed",
    Component: Feed
  }
])

createRoot(document.getElementById('root')).render(
  <RouterProvider router={router} />
)
