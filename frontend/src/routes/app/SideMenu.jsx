import { Link } from "react-router-dom";
import { userTypes } from "../../global/Utility";
import style from "./SideMenu.module.css";

function SideMenu({ userType }) {
  console.log(userType);
  return (
    // made as per description.
    // I prefer to make different component for each menus.
    // Added an example of dashboard navigation
    <section className={style.sideMenu}>
      <div className={style.head} >Menu Options</div>
      
      {userType === userTypes.AdminUser && <div className={style.option} ><Link to="dashboard">Dashboard (Admin/Normal)</Link></div>}
      {userType === userTypes.AdminUser && <div className={style.option} >User List (Admin/Normal)</div>}
      {userType === userTypes.AdminUser && <div className={style.option} >User Management (Admin)</div>}
      {userType === userTypes.NormalUser && <div className={style.option} ><Link to="dashboard">Dashboard</Link></div>}
      {userType === userTypes.NormalUser && <div className={style.option} >User List</div>}
      {userType === userTypes.NormalUser && <div className={style.option} >User Management</div>}
    </section>
  )
}

export default SideMenu