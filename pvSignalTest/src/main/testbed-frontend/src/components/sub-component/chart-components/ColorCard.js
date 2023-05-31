import React,{ useState} from 'react'
import Card from 'react-bootstrap/Card';

function ColorCard(props) {

	return (
		<Card
			bg={props.passColor.toLowerCase()}
			key={props.passColor}
			text={props.passColor.toLowerCase() === 'light' ? 'dark' : 'white'}
			style={{ width: '18rem' }}
			className="mb-2">
			
			<Card.Body>
				<Card.Title>{props.count}</Card.Title>
				<Card.Text>
					{props.passColor == 'Success' ? "Passed Test Cases" : "Failed Test Cases" }
				</Card.Text>
			</Card.Body>
		</Card>
	);
}

export default ColorCard;