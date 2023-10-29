import { fireEvent, render, screen } from '@testing-library/react';
import CreatePassenger from '../../view/CreatePassenger';

test('should test create passenger', () => {
  const { container } = render(<CreatePassenger />);
  container.getElementsByClassName(".passenger-name").item("Jonh Doe")
  container.getElementsByClassName(".passenger-email").item("john.doe@gmail.com")
  container.getElementsByClassName(".passenger-document").item("83432616074")
  const button = screen.getByTestId("button-id");
  fireEvent.click(button)
  const linkElement = screen.getByTestId("passenger-id")
  setTimeout(() => {
    expect(linkElement).toHaveLength(36);
  }, 200)
});


test('should test not create passenger with invalid name', () => {
  const { container } = render(<CreatePassenger />);
  container.getElementsByClassName(".passenger-name").item("Jonh")
  container.getElementsByClassName(".passenger-email").item("john.doe@gmail.com")
  container.getElementsByClassName(".passenger-document").item("83432616074")
  const button = screen.getByTestId("button-id");
  fireEvent.click(button)
  const linkElement = screen.getByTestId("error")
  expect(linkElement.textContent).toBe("Invalid name");
});