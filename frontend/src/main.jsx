import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import Login from "./pages/Login.jsx"
import Chat from "./pages/Chat.jsx"
import { BrowserRouter , createBrowserRouter, RouterProvider } from "react-router-dom";

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
    path: "/chat",
    Component: Chat
  }
])


createRoot(document.getElementById('root')).render(
  <RouterProvider router={router} />
)
