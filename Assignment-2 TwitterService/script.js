// author: Mohaimin Iqbal gazi

document.addEventListener("DOMContentLoaded", () => {
    const createTweetForm = document.getElementById("createTweetForm");
    const deleteTweetForm = document.getElementById("deleteTweetForm");
    const createResult = document.getElementById("createResult");
    const deleteResult = document.getElementById("deleteResult");

    createTweetForm.addEventListener("submit", async (e) => {
        e.preventDefault();
        const tweetText = document.getElementById("tweetText").value;
        if (tweetText.trim() === "") {
            createResult.textContent = "Please enter tweet text.";
            return;
        }

            const response = await fetch("http://localhost:8080/create", {
                method: "POST",
                headers: {
                    "Content-Type": "text/plain",
                },
                body: tweetText,
            });
                const data = await response.json();
                alert(`Tweet created successfully!\nTweet ID: ${data.id}\nTweet Text: ${data.text}`);
    });

    deleteTweetForm.addEventListener("submit", async (e) => {
        e.preventDefault();
        const deleteTweetId = document.getElementById("deleteTweetId").value;
        if (deleteTweetId.trim() === "") {
            deleteResult.textContent = "Please enter tweet ID.";
            return;
        }

        try {
            const response = await fetch(`http://localhost:8080/delete/${deleteTweetId}/`, {
                method: "DELETE",
            });

            if (response.ok) {
                deleteResult.textContent = "Tweet deleted successfully!";
            } else {
                deleteResult.textContent = "Error deleting tweet.";
            }
        } catch (error) {
            console.error("Error:", error);
            deleteResult.textContent = "Error deleting tweet.";
        }
    });
});
