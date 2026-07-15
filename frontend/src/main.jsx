import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import Login from "./pages/Login.jsx"
import Chat from "./pages/Chat.jsx"
import SignIn from "./pages/SignIn.jsx"
import Feed from './pages/Feed.jsx'
import Profile from "./pages/Profile.jsx"
import Post from './pages/Post.jsx'

import ProtectedRoute from './utils/ProtectedRoute.jsx'

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
    path: "/signin",
    Component: SignIn
  },
  {
    element: <ProtectedRoute />,
    children: [
      {
        path: "/chat",
        Component: Chat
      },
      {
        path: "/feed",
        Component: Feed
      },
      {
        path: "/profile",
        Component: Profile
      },
      {
        path: "/post",
        Component: Post
      }
    ]}
])

createRoot(document.getElementById('root')).render(
  <RouterProvider router={router} />
)
