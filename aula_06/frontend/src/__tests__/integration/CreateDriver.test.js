import { fireEvent, render, screen } from '@testing-library/react';
import CreateDriver from '../../view/CreateDriver';

test('should test create driver', () => {
  const { container } = render(<CreateDriver />);
  container.getElementsByClassName(".driver-name").item("Jonh Doe")
  container.getElementsByClassName(".driver-email").item("john.doe@gmail.com")
  container.getElementsByClassName(".driver-document").item("83432616074")
  const button = screen.getByTestId("button-id");
  fireEvent.click(button)
  const linkElement = screen.getByTestId("driver-id")
  setTimeout(() => {
    expect(linkElement).toHaveLength(36);
  }, 200)
});

test('should test not create driver with invalid name', () => {
  const { container } = render(<CreateDriver />);
  container.getElementsByClassName(".driver-name").item("Jonh")
  container.getElementsByClassName(".driver-email").item("john.doe@gmail.com")
  container.getElementsByClassName(".driver-document").item("83432616074")
  const button = screen.getByTestId("button-id");
  fireEvent.click(button)
  const linkElement = screen.getByTestId("error")
  expect(linkElement.textContent).toBe("Invalid name");
});