import {
  Container,
  Nav,
  Navbar,
  NavDropdown,
  Form,
  Button,
  Row,
  Col,
} from "react-bootstrap";
import { useEffect, useState, useContext } from "react";
import logo from "../images/logo.png";
import avatar from "../images/blank_avatar.png";
import { useNavigate, Link } from "react-router-dom";
import Apis, { endpoints } from "../configs/Apis";
import { MyUserContext } from "../App";
import MySpinner from "./MySpinner";

const Header = () => {
  const [user, dispatch] = useContext(MyUserContext);
  const [cities, setCities] = useState([]);
  const [cates, setCates] = useState([]);
  const [kw, setKw] = useState("");
  const nav = useNavigate();

  const loadCities = async () => {
    let res = await Apis.get(endpoints["cities"]);
    setCities(res.data);
  };

  const loadCates = async () => {
    let res = await Apis.get(endpoints["categories"]);
    setCates(res.data);
  };

  useEffect(() => {
    loadCities();
    loadCates();
  }, []);

  const search = (evt) => {
    evt.preventDefault();
    nav(`\?kw=${kw}`);
  };

  const logout = () => {
    dispatch({
      type: "logout",
    });
  };

  if (cities === null) return <MySpinner />;
  if (cates === null) return <MySpinner />;

  return (
    <>
      <Navbar data-bs-theme="light" expand="lg" className="bg-body-tertiary">
        <Container>
          <Navbar.Brand>
            <Link to="/">
              <img
                alt=""
                src={logo}
                width="50"
                height="50"
                className="d-inline-block align-top"
              />{" "}
            </Link>
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="mx-auto">
              <NavDropdown title="Tỉnh thành" id="basic-nav-dropdown">
                {cities &&
                  cities.map((c) => (
                    <NavDropdown.Item key={c.id}>
                      <Link
                        to={`/?cityId=${c.id}`}
                        className="drop-item"
                        style={{ textDecoration: "none" }}
                      >
                        {c.name}
                      </Link>
                    </NavDropdown.Item>
                  ))}
              </NavDropdown>
              <NavDropdown
                title="Danh mục"
                id="basic-nav-dropdown"
                className="nav-dropdown-cate"
              >
                {cates &&
                  cates.map((c) => (
                    <NavDropdown.Item key={c.id}>
                      <Link
                        to={`/?cateResId=${c.id}`}
                        className="drop-item"
                        style={{ textDecoration: "none" }}
                      >
                        {c.name}
                      </Link>
                    </NavDropdown.Item>
                  ))}
              </NavDropdown>

              <Form onSubmit={search} inline>
                <Row>
                  <Col xs="auto">
                    <Form.Control
                      type="text"
                      placeholder="Địa điểm , món ăn..."
                      value={kw}
                      onChange={(e) => setKw(e.target.value)}
                      className="mr-sm-2"
                      aria-label="Search"
                    />
                  </Col>
                  <Col xs="auto">
                    <Button
                      className="btn-search"
                      variant="outline-success"
                      type="submit"
                    >
                      Tìm kiếm
                    </Button>
                  </Col>
                </Row>
              </Form>
            </Nav>
            <NavDropdown
              title={
                <div className="pull-left">
                  <img
                    className="thumbnail-image"
                    src={ avatar }
                    roundedCircle
                    width="45"
                    height="35"
                    alt="user"
                  />
                </div>
              }
              id="basic-nav-dropdown"
            >
              {user === null ? (
                <>
                  <NavDropdown.Item>
                    <Link className="drop-item" to="/login">
                      Đăng nhập
                    </Link>
                  </NavDropdown.Item>
                  <NavDropdown.Item>
                    <Link className="drop-item" to="/register">
                      Đăng ký
                    </Link>
                  </NavDropdown.Item>
                </>
              ) : (
                <>
                  <NavDropdown.Item>
                    <Link className="drop-item" to="/user">
                      Chào {user.firstName}!
                    </Link>
                  </NavDropdown.Item>
                  { user.userRole !== "ROLE_ADMIN" ? (
                    <>
                      <NavDropdown.Item>
                        <Link className="drop-item" to="/user">
                          Thông tin cá nhân
                        </Link>
                      </NavDropdown.Item>
                    
                    {
                      user.userRole === "ROLE_RESTAURANT" ? (
                      <>
                      <NavDropdown.Item>
                        <Link className="drop-item" to="/seller">
                          Thông tin nhà hàng
                        </Link>
                      </NavDropdown.Item>
                      </>
                      
                    ): (
                      <>
                      <NavDropdown.Item>
                        <Link className="drop-item" to="/restaurant">
                          Đăng ký nhà hàng
                        </Link>
                      </NavDropdown.Item>
                      </>
                    )}
                    </>
                  ) : (
                    <>
                    </>
                  )}
                  <NavDropdown.Item>
                    <Button variant="secondary" onClick={logout}>
                    <Link className="drop-item" to="/login">
                          Đăng xuất
                        </Link>
                    </Button>
                  </NavDropdown.Item>
                </>
              )}
            </NavDropdown>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    </>
  );
};

export default Header;
