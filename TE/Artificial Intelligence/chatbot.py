import streamlit as st
import random

# Dictionary of responses
responses = {
    "engine": [
        "It sounds like there might be an issue with your engine. Check the oil level and engine temperature.",
        "Engine trouble can be serious. Have you noticed any strange noises or smells?",
        "Make sure to monitor your engine's performance closely. It might need a professional check-up."
    ],
    "tire": [
        "Ensure your tires are properly inflated and check for punctures.",
        "Tire issues can affect your driving experience. Consider having them inspected.",
        "Are your tires worn out? It might be time for a replacement."
    ],
    "battery": [
        "Check the battery terminals for corrosion and ensure it's charged.",
        "If your car won't start, the battery could be the issue. How old is it?",
        "Battery problems are common, especially in cold weather. Consider testing it with a voltmeter."
    ],
    "greetings": [
        "Hello! How can I assist you with your car today?",
        "Hi there! What can I do for your vehicle today?",
        "Greetings! Ready to help with your car needs."
    ],
    "contact": [
        "You can contact us at 555-1234. We're here to help!",
        "Reach out to our support at support@carhelp.com or call us on 555-1234.",
        "For assistance, call 555-1234 or email support@carhelp.com."
    ],
    "bye": [
        "Goodbye! If you have more questions later, feel free to ask.",
        "Bye! Don't hesitate to return if you need more car advice.",
        "Farewell! Hope your car issues get resolved soon."
    ],
    "location": [
        "Our shop is located at 123 Car St., Autotown.",
        "You can find us at 123 Car St., Autotown. We're open Mon-Fri.",
        "Visit us at 123 Car St., Autotown. We'd love to help in person!"
    ],
    "unknown": [
        "I'm not sure about that. Could you provide more details or maybe check with a professional mechanic?",
        "Can you give more information or describe the problem further?",
        "I don’t have enough information. It might be best to consult a professional mechanic."
    ]
}

def get_response(user_input):
    user_input = user_input.lower()
    # Match greeting, contact, and bye messages
    if any(greet in user_input for greet in ["hi", "hello", "hey"]):
        return random.choice(responses["greetings"])
    elif any(cont in user_input for cont in ["contact", "phone", "email"]):
        return random.choice(responses["contact"])
    elif any(bye in user_input for bye in ["bye", "goodbye", "see you"]):
        return random.choice(responses["bye"])
    elif any(loc in user_input for loc in ["where", "location", "address"]):
        return random.choice(responses["location"])
    else:
        for key in responses:
            if key in user_input:
                return random.choice(responses[key])
        return random.choice(responses["unknown"])

def handle_input():
    user_input = st.session_state.user_input
    if user_input:
        response = get_response(user_input)
        st.session_state.chat_history.append(('user', user_input))
        st.session_state.chat_history.append(('assistant', response))
        st.session_state.user_input = ""  # Clear the input after processing

st.title('Nitro Mechanics')

if 'chat_history' not in st.session_state:
    st.session_state.chat_history = []
    st.session_state.user_input = ""

# Display existing chat messages
for role, message in st.session_state.chat_history:
    with st.chat_message(role):
        st.write(message)

# Handle new user input
st.text_input("How can I help you with your car today?", key="user_input", on_change=handle_input)
