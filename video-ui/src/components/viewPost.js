import React, { useState, useEffect, Fragment } from 'react';
import { BASE_URL } from '../urls/baseUrl';
import { Col, Row, Card, Container, Button } from 'react-bootstrap';
import { GetAllVideos } from '../urls/videoService';
import useCollapse from 'react-collapsed';
import { Link } from 'react-router-dom';
import axios from 'axios';
const ViewPost = () => {

    const [isExpanded, setExpanded] = useState(false)
    const { getCollapseProps, getToggleProps } = useCollapse({ isExpanded })

    const [posts, setPosts] = useState([]);
    const [loading, setLoading] = useState(false)
    //Gatting All videos .
    const getAllVideos = async () => {
        try {
            const response = await GetAllVideos();
            setPosts(response.data);
            console.log(response.data);
            console.log(response);
            setLoading(true);

        } catch (error) {
            alert(error.message);
        }
    };
    useEffect(() => {
        getAllVideos();
    }, []);
    return (
        <div>
            <Container className="mb-2 p-3">
                <Button variant="primary" size="lg" href="/">Back to add Video</Button>
            </Container>
            <Container >
                <Row>
                    {loading && posts.map((post) => (
                        <Col sm={12} md={6} lg={3} key={post.id} >
                            <Card className='my-3 p-3 rounded h-90'>
                                <Card.Header>Videos </Card.Header>
                                <Card.Body>
                                    <div style={{ maxWidth: "40%" }}>
                                        <video controls width="250" height="200">
                                            <source src={BASE_URL + '/play/' + post.id} type="video/mp4" alt="" />
                                        </video>
                                    </div>
                                    <Card.Title>{post.title}</Card.Title>
                                    <Card.Subtitle><Card.Text><strong style={{ color: "blue" }}>{post.tags}</strong></Card.Text></Card.Subtitle>
                                    <div >
                                        <p {...getCollapseProps()}>{post.description}</p>
                                        <Button size="xs" variant="white"
                                            {...getToggleProps({
                                                onClick: () => setExpanded((prevExpanded) => !prevExpanded),
                                            })}
                                        >
                                            {isExpanded ? 'Read less' : 'Read more'}
                                        </Button>

                                    </div>

                                </Card.Body>
                            </Card></Col>
                    )
                    )}
                </Row>
            </Container>
        </div>
    )
}

export default ViewPost