import {Link} from "react-router-dom";

export const Popular = (props) => {
    return (
        <Link to={props.linkTo}>
            <div>
                <img src={props.imgSrc} alt={props.imgAlt}/>
                <span>{props.label}</span>
            </div>
        </Link>
    );
}