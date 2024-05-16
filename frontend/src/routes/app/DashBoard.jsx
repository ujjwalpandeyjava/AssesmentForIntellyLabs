import { lazy, useContext } from "react";
import { userTypes } from "../../global/Utility"
import UserAuthContext from "../../global/UserAuthProvider";
import SideMenu from "./SideMenu";
import style from "./DashBoard.module.css";

const Component = {
  [userTypes.AdminUser]: lazy(() => import("./adminUser/AdminUser")),
  [userTypes.NormalUser]: lazy(() => import("./normalUser/NormalUser")),
  [userTypes.Guest]: lazy(() => import("./guestUser/GuestUser"))
}

function DashBoard() {
  // eslint-disable-next-line no-unused-vars
  const { userAuthDetails, setUserAuthDetails } = useContext(UserAuthContext);
  const MyLazyComp = Component[userAuthDetails.userType];
  if (userAuthDetails?.isLoggedIn === true) {
    return (
      <div className={style.home}>
        <SideMenu userType={userAuthDetails.userType} />
        <section className={style.interactionSection}>
          <MyLazyComp />
        </section>
      </div>)
  } else {

    return (
      <div>
        <h1>Please Login</h1>
      </div>
    )
  }
}

export default DashBoard