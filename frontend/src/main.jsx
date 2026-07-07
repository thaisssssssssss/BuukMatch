import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import Login from "./pages/Login.jsx"
import Chat from "./pages/Chat.jsx"
import SignIn from "./pages/SignIn.jsx"

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
    path: "/chat",
    Component: Chat
  },
  {
    path: "/feed",
    Component: Feed
  },
  {
    path: "/signin",
    Component: SignIn
  }
])

createRoot(document.getElementById('root')).render(
  <RouterProvider router={router} />
)
