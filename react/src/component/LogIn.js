import { useContext, useState } from "react";
import { Button, Form } from "react-bootstrap";
import cookie from "react-cookies";
import "./LogIn.css";
import { Navigate, useSearchParams } from "react-router-dom";
import { MyUserContext } from "../App";
import Apis, { authApi, endpoints } from "../configs/Apis";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const LogIn = () => {
  const [user, dispatch] = useContext(MyUserContext);
  const [username, setUsername] = useState();
  const [password, setPassword] = useState();
  const [q] = useSearchParams();

  const login = (evt) => {
    evt.preventDefault();

    const notify = () => {
      toast.error("Thông tin đăng nhập chưa chính xác", {
        position: "bottom-right",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "light",
      });
    };

    const process = async () => {
      try {
        let res = await Apis.post(endpoints["login"], {
          userName: username,
          password: password,
        });
        cookie.save("token", res.data);

        let { data } = await authApi().get(endpoints["current-user"]);
        cookie.save("user", data);
        console.log(data);

        dispatch({
          type: "login",
          payload: data,
        });
      } catch (err) {
        notify();
      }
    };

    process();
  };

  if (user !== null) {
    let next = q.get("next") || "/";
    return <Navigate to={next} />;
  }

  return (
    <>
      <div className="content-login">
        <h1 className="text-center">ĐĂNG NHẬP NGƯỜI DÙNG</h1>

        <Form onSubmit={login}>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Tên đăng nhập</Form.Label>
            <Form.Control
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              type="text"
              placeholder="Tên đăng nhập"
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Form.Label>Mật khẩu</Form.Label>
            <Form.Control
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              type="password"
              placeholder="Mật khẩu"
            />
          </Form.Group>
          <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
            <Button variant="success" type="submit">
              Đăng nhập
            </Button>
          </Form.Group>
        </Form>
      </div>
      <ToastContainer
        position="bottom-right"
        autoClose={5000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme="light"
      />
    </>
  );
};

export default LogIn;
