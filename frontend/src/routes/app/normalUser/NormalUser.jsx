import { Outlet } from "react-router"

function NormalUser() {
	return (
		<div>
			NormalUser
			<Outlet />
		</div>
	)
}

export default NormalUser