import { Outlet } from "react-router"

function AdminUser() {
  return (
    <div>
      <h2>
        AdminUser
        <Outlet />
      </h2>
    </div>
  )
}

export default AdminUser