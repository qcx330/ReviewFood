import { useEffect, useRef, useState } from "react";
import { Alert, Button, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import Apis, { endpoints } from "../configs/Apis";
import MySpinner from "../layout/MySpinner";
import "./LogIn.css";
import { ToastContainer, toast } from "react-toastify";

const Register = () =>{
    const [user, setUser] = useState({
        "firstName": "", 
        "lastName": "",
        "email": "",
        "phone": "", 
        "userName": "", 
        "password": "", 
        "confirmPass": "",
    });
    const [err, setErr] = useState(null);
    const [loading, setLoading] = useState(false);
    const [city, setCity] = useState([]);
    const [selected, setSelected] = useState(null);
    const avatar = useRef();
    const nav = useNavigate();


    useEffect(() => {

        const loadCities = async () => {
          let res = await Apis.get(endpoints["cities"]);
          setCity(res.data);
          setSelected(res.data[0].id);
        };
    
        loadCities();
      }, []);

    const notify = () => {
        toast.success("Đăng ký thành công", {
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

    // const selectAvatar = (evt) =>{
    //     setUser({...user, ["file"]: evt.target.files[0]})
    // }

    const select = (evt) =>{
        setSelected(evt.target.value);
    }
    const register = (evt) => {
        evt.preventDefault();

        const process = async () => {
            let form = new FormData();

            for (let field in user)
                if (field !== "confirmPass")
                    form.append(field, user[field]);

            // form.append("cityId", JSON.stringify(selected));
            form.append("cityId", selected);
            form.append("avatar", avatar.current.files[0]);

            setLoading(true)

            try {
            let res = await Apis.post(endpoints['register'], form);
            if (res.status === 201) {
                notify();
                nav("/login");
            } else
            setErr("Hệ thống bị lỗi!");
            }catch (e) {
                console.error(e);
                console.log(user);
                for (const [key, value] of form.entries()) {
                    console.log(`${key}: ${value}`);
                  }
            }
        }

        if (user.password === user.confirmPass)
            process();
        else {
            setErr("Mật khẩu KHÔNG khớp!");
        }
    }

    const change = (evt, field) => {
        setUser({...user, [field]: evt.target.value})
        // setUser({...user, ["cityId"]: selected})
        setUser(current => {
            return {...current, [field]: evt.target.value}
        })
    }

    return <>
    <div id="register">
        <h1 className="text-center">ĐĂNG KÝ NGƯỜI DÙNG</h1>

        {err === null?"":<Alert variant="danger">{err}</Alert>}

        <Form onSubmit={register}>
            <Form.Group className="mb-3">
                <Form.Label>Tên</Form.Label>
                <Form.Control value={user.firstName} type="text" onChange={(e) => change(e, "firstName")} placeholder="Tên" required />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Họ và chữ lót</Form.Label>
                <Form.Control value={user.lastName} type="text" onChange={(e) => change(e, "lastName")} placeholder="Họ và chữ lót" required />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Email</Form.Label>
                <Form.Control value={user.email} type="email" onChange={(e) => change(e, "email")} placeholder="Email" />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Điện thoại</Form.Label>
                <Form.Control value={user.phone} type="tel" onChange={(e) => change(e, "phone")} placeholder="Điện thoại" />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Tên đăng nhập</Form.Label>
                <Form.Control value={user.userName} onChange={(e) => change(e, "userName")} type="text" placeholder="Tên đăng nhập" required />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Mật khẩu</Form.Label>
                <Form.Control value={user.password} onChange={(e) => change(e, "password")} type="password" placeholder="Mật khẩu" required />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Xác nhận mật khẩu</Form.Label>
                <Form.Control value={user.confirmPass} onChange={(e) => change(e, "confirmPass")} type="password" placeholder="Xác nhận mật khẩu" required />
            </Form.Group>
            <Form.Group className="mb-3">
            <Form.Label>Thành phố</Form.Label>
          <Form.Select onChange={select} value={selected}>
          {city && city.map((c)=>(
            <option key={c.id} value={c.id}>{c.name}</option>
          ))}
          </Form.Select>
          </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label>Ảnh đại diện</Form.Label>
                <Form.Control type="file" ref={avatar} />
            </Form.Group>
            <Form.Group className="mb-3">
                {loading === true?<MySpinner />: <Button variant="success" type="submit">Đăng ký</Button>}
                
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
}

export default Register;