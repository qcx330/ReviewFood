import { useContext, useEffect, useRef, useState } from "react";
import { Alert, Button, Form } from "react-bootstrap";
import MySpinner from "../layout/MySpinner";
import "./LogIn.css";
import { ToastContainer, toast } from "react-toastify";
import { MyUserContext } from "../App";
import Apis, { endpoints } from "../configs/Apis";

const CreateRes = () => {
  const [user] = useContext(MyUserContext);
  const [res, setRes] = useState({
    "name": "",
    "address": "",
    "categoryResId": "",
    "cityId": "",
  });
  const [err, setErr] = useState(null);
  const [loading, setLoading] = useState(false);
  const [city, setCity] = useState([]);
  const [cates, setCates] = useState([]);
  const image = useRef();
  // const nav = useNavigate();

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

  useEffect(() => {

    const loadCities = async () => {
      let res = await Apis.get(endpoints["cities"]);
      setCity(res.data);
    };

    const loadCates = async () => {
      let res = await Apis.get(endpoints["categories"]);
      setCates(res.data);
    };

    loadCities();
    loadCates();
  }, []);

  const register = (evt) => {
    evt.preventDefault();

    const process = async () => {
      let form = new FormData();

      // for (let field in res)
      //   if (field !== "confirmPass") form.append(field, res[field]);

      form.append("image", image.current.files[0]);

      setLoading(true);
      try {
        let res = await Apis.post(endpoints["create-restaurant"], form);
        if (res.status === 201) {
          notify();
          // nav("/login");
        } else setErr("Hệ thống bị lỗi!");
      } catch (e) {
        console.error(e);
        console.log(user);
        console.log(form);
      }
    };
    process();
  };

  const change = (evt, field) => {
    setRes({ ...res, [field]: evt.target.value });
    setRes((current) => {
      return { ...current, [field]: evt.target.value };
    });
  };
  return (
    <>
      <div id="register">
        <h1 className="text-center">ĐĂNG KÝ NHÀ HÀNG</h1>

        {err === null ? "" : <Alert variant="danger">{err}</Alert>}

        <Form onSubmit={register}>
          <Form.Group className="mb-3">
            <Form.Label>Tên</Form.Label>
            <Form.Control
              value={res.name}
              type="text"
              onChange={(e) => change(e, "name")}
              placeholder="Tên"
              required
            />
          </Form.Group>
          <Form.Group className="mb-3">
            <Form.Label>Địa chỉ</Form.Label>
            <Form.Control
              value={res.address}
              type="text"
              onChange={(e) => change(e, "address")}
              placeholder="Địa chỉ"
              required
            />
          </Form.Group>
          <Form.Group className="mb-3">
            <Form.Label>Thành phố</Form.Label>
          <Form.Select>
          {city && city.map((c)=>(
            <option key={c.id}>{c.name}</option>
          ))}
          </Form.Select>
          </Form.Group>
          <Form.Group className="mb-3">
            <Form.Label>Danh mục</Form.Label>
          <Form.Select>
          {cates && cates.map((c)=>(
            <option key={c.id}>{c.name}</option>
          ))}
          </Form.Select>
          </Form.Group>
          <Form.Group className="mb-3">
            <Form.Label>Ảnh nhà hàng</Form.Label>
            <Form.Control type="file" ref={image} />
          </Form.Group>
          <Form.Group className="mb-3">
            {loading === true ? (
              <MySpinner />
            ) : (
              <Button variant="success" type="submit" onClick={register}>
                Đăng ký
              </Button>
            )}
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

export default CreateRes;
