import { fireEvent, render, screen } from '@testing-library/react';
import PassengerRide from '../../view/PassengerRide';


test('should test passenger calculate a ride price', () => {
    const { container } = render(<PassengerRide />);
    container.getElementsByClassName(".ride-from-lat").item("-27.584905257808835")
    container.getElementsByClassName(".ride-from-long").item("-48.545022195325124")
    container.getElementsByClassName(".ride-to-lat").item("-27.496887588317275")
    container.getElementsByClassName(".ride-to-long").item("-48.522234807851476")
    const button = screen.getByTestId("calculate-ride-button");
    fireEvent.click(button)
    const linkElement = screen.getByTestId("ride-price")
    setTimeout(() => {
      expect(linkElement).toBe(21);
    }, 200)
  });