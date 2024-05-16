import { Outlet } from "react-router"

function GuestUser() {
  return (
    <div>
      GuestUser
      <Outlet />
    </div>
  )
}

export default GuestUser