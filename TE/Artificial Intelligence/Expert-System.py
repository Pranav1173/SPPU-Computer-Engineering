class HelpDeskExpertSystem:
    def __init__(self):
        # Define simple rules for categorizing issues
        self.rules = {
            "connectivity": ["internet", "connection", "network", "wifi", "ethernet"],
            "hardware": ["laptop", "keyboard", "mouse", "monitor", "printer"],
            "software": ["installation", "error", "crash", "freeze", "bug"],
            "account": ["login", "password", "username", "access", "account"]
        }

    def classify_issue(self, description):
        # Lowercase the description to standardize the input
        description = description.lower()
        
        # Check if description matches any category keywords
        for category, keywords in self.rules.items():
            if any(keyword in description for keyword in keywords):
                return category
        
        return "unknown"  # If no category is matched

    def suggest_solution(self, category):
        # Provide detailed solutions based on the category
        solutions = {
            "connectivity": [
                "Ensure your device's Wi-Fi is turned on and you're within range of the router.",
                "Check for any physical obstructions that might block the signal.",
                "Restart your router or modem by unplugging it for 30 seconds and then plugging it back in.",
                "Contact your internet service provider to check if there are outages in your area."
            ],
            "hardware": [
                "Check all connections, including power cables and USBs, for secure fitting.",
                "Try turning the device off and on again.",
                "Check if the hardware drivers are up to date.",
                "If the issue persists, the device may need repairs or replacement."
            ],
            "software": [
                "Restart the application or computer to clear temporary glitches.",
                "Install the latest updates for your software and operating system.",
                "Check for error messages or logs that can provide more details about the issue.",
                "Uninstall and reinstall the problematic software."
            ],
            "account": [
                "Ensure you are entering the correct username and password.",
                "Reset your password using the 'Forgot Password' link.",
                "Clear your browser's cache and cookies and try logging in again.",
                "Contact support if you suspect your account has been compromised."
            ],
            "unknown": [
                "Please provide more detailed information about the issue.",
                "Try general troubleshooting steps like restarting your device.",
                "Contact the help desk for further assistance."
            ]
        }

        # Format the solution output
        response = solutions.get(category, ["Please contact the help desk for further assistance."])
        return "\n".join(f"- {s}" for s in response)

    def handle_query(self, description):
        category = self.classify_issue(description)
        solution = self.suggest_solution(category)
        return solution

# Example use case
if __name__ == "__main__":
    help_desk = HelpDeskExpertSystem()
    print("Welcome to the Help Desk Support System!")

    while True:
        user_input = input("\nPlease describe your IT problem or type 'exit' to quit: ")
        if user_input.lower() == 'exit':
            print("Exiting the Help Desk Support System. Goodbye!")
            break

        solution = help_desk.handle_query(user_input)
        print("\nSuggested Solution:")
        print(solution)
