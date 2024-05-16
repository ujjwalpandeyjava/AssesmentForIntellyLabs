import { useContext, useState } from "react";
import { toast } from "react-toastify";
import apiEndPoints from "../../action/api";
import style from "../register/RegistrationForm.module.css";
import { useNavigate } from "react-router";
// import { userTypes } from "../../global/Utility";
import UserAuthContext from "../../global/UserAuthProvider";

function Login() {
  const nav = useNavigate();
  const { userAuthDetails, setUserAuthDetails } = useContext(UserAuthContext);


  const handleUpdate = (data) => {
    let clonedAuth = structuredClone(userAuthDetails);
    clonedAuth.email = data.email;
    clonedAuth.isLoggedIn = data.isLoggedIn;
    clonedAuth.userType = data.userType;

    setUserAuthDetails(clonedAuth)
    nav("/app");
    toast.success(`Welcome ${clonedAuth.userType}, '${clonedAuth.email}'`, {
      autoClose: 1000
    });
    setFormData({ name: '', email: '', password: '' });
  };

  const [formData, setFormData] = useState({
    email: '',
    password: ''
  });

  const handleChange = (event) => {
    setFormData({ ...formData, [event.target.name]: event.target.value });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    if (!formData.email || !formData.password) {
      toast("Please enter details!")
      return;
    }
    apiEndPoints.AUTH().login({ body: formData })
      .then(resp => {
        if (resp.status === 200) {
          return resp.data;
        } else {
          toast.error(resp.data.message);
          return;
        }
      }).then(resp => {
        handleUpdate(resp);
      }).catch(err => {
        console.log(err);

      });
  };

  return (
    <div className={`${style.loginForm} ${style.registration}`}>
      <form onSubmit={handleSubmit} className={style.form}>

        <h1>Login Form</h1>

        <label htmlFor="email" className="required">Email</label>
        <input type="email" id="email" name="email" value={formData.email} onChange={handleChange} required />

        <label htmlFor="password" className="required">Password</label>
        <input type="password" id="password" name="password" value={formData.password} onChange={handleChange} />

        <button type="submit">login</button>
      </form>
    </div>
  );
}

export default Login