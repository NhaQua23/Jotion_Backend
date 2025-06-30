insert into workspaces (name, created_at, updated_at, editable, user_id) values ('Workspace 4', '2000-01-01', '2000-01-01', true, 1);
insert into workspaces (name, created_at, updated_at, editable, user_id) values ('Workspace 5', '2000-01-01', '2000-01-01', true, 1);
insert into workspaces (name, created_at, updated_at, editable, user_id) values ('Workspace 6', '2000-01-01', '2000-01-01', true, 2);
insert into workspaces (name, created_at, updated_at, editable, user_id) values ('Workspace 7', '2000-01-01', '2000-01-01', true, 2);
insert into workspaces (name, created_at, updated_at, editable, user_id) values ('Workspace 8', '2000-01-01', '2000-01-01', true, 3);
insert into workspaces (name, created_at, updated_at, editable, user_id) values ('Workspace 9', '2000-01-01', '2000-01-01', true, 3);

insert into pages (title, created_at, updated_at, workspace_id, author_id) values ('Page 1', '2000-01-01', '2000-01-01', 4, 1);
insert into pages (title, created_at, updated_at, workspace_id, author_id) values ('Page 2', '2000-01-01', '2000-01-01', 5, 1);
insert into pages (title, created_at, updated_at, workspace_id, author_id) values ('Page 3', '2000-01-01', '2000-01-01', 6, 2);
insert into pages (title, created_at, updated_at, workspace_id, author_id) values ('Page 4', '2000-01-01', '2000-01-01', 7, 2);
insert into pages (title, created_at, updated_at, workspace_id, author_id) values ('Page 5', '2000-01-01', '2000-01-01', 8, 3);
insert into pages (title, created_at, updated_at, workspace_id, author_id) values ('Page 6', '2000-01-01', '2000-01-01', 9, 3);

-- insert into tags (name, color, created_at, updated_at, created_by_id) values ('Tag 1', 'RED', '2000-01-01', '2000-01-01', 1);
-- insert into tags (name, color, created_at, updated_at, created_by_id) values ('Tag 2', 'BLUE', '2000-01-01', '2000-01-01', 1);
-- insert into tags (name, color, created_at, updated_at, created_by_id) values ('Tag 3', 'YELLOW', '2000-01-01', '2000-01-01', 1);
-- insert into tags (name, color, created_at, updated_at, created_by_id) values ('Tag 4', 'BLACK', '2000-01-01', '2000-01-01', 1);
-- insert into tags (name, color, created_at, updated_at, created_by_id) values ('Tag 5', 'WHITE', '2000-01-01', '2000-01-01', 1);
-- insert into tags (name, color, created_at, updated_at, created_by_id) values ('Tag 6', 'NAVY', '2000-01-01', '2000-01-01', 1);

-- insert into page_tags (page_id, tag_id) values (1, 1);
-- insert into page_tags (page_id, tag_id) values (2, 2);
-- insert into page_tags (page_id, tag_id) values (3, 3);
-- insert into page_tags (page_id, tag_id) values (4, 4);
-- insert into page_tags (page_id, tag_id) values (5, 5);
-- insert into page_tags (page_id, tag_id) values (6, 6);

-- insert into blocks (block_type, position, created_at, updated_at, page_id, created_by_id) values ('paragraph', 1, '2000-01-01', '2000-01-01', 1, 1);
-- insert into text_block (id, content) values (1, 'Hello Jotion!!!');
-- insert into blocks (block_type, position, created_at, updated_at, page_id, created_by_id) values ('paragraph', 2, '2000-01-01', '2000-01-01', 1, 1);
-- insert into text_block (id, content) values (2, 'I''m using Jotion');
-- insert into blocks (block_type, position, created_at, updated_at, page_id, created_by_id) values ('heading', 3, '2000-01-01', '2000-01-01', 1, 1);
-- insert into text_block (id, content) values (3, 'Heading');
-- insert into blocks (block_type, position, created_at, updated_at, page_id, created_by_id) values ('bulletListItem', 4, '2000-01-01', '2000-01-01', 1, 1);
-- insert into text_block (id, content) values (4, 'Bullet List Item');
-- insert into blocks (block_type, position, created_at, updated_at, page_id, created_by_id) values ('checkListItem', 5, '2000-01-01', '2000-01-01', 1, 1);
-- insert into text_block (id, content) values (5, 'Check List Item');

-- insert into blocks (block_type, created_at, updated_at, page_id, created_by_id) values ('IMAGE', '2000-01-01', '2000-01-01', 1, 2);
-- insert into image_block (id, image_url, caption) values (3, 'url', 'test url');
--
-- insert into blocks (block_type, created_at, updated_at, page_id, created_by_id) values ('TODO', '2000-01-01', '2000-01-01', 1, 2);
-- insert into todo_block (id, content, completed, due_date) values (4, 'Writing', FALSE, '2024-07-22');
--
-- insert into blocks (block_type, created_at, updated_at, page_id, created_by_id) values ('FLASHCARD', '2000-01-01', '2000-01-01', 1, 2);
-- insert into flashcard_block (id, question, answer) values (5, 'Question 1', 'Answer 1');
--
-- insert into blocks (block_type, created_at, updated_at, page_id, created_by_id) values ('TABLE', '2000-01-01', '2000-01-01', 1, 2);
-- insert into table_block (id, table_data) values (6, '{"columns": ["Name", "Age", "Email"], "rows": [{"data": ["John Doe", "30", "john.doe@example.com"]}, {"data": ["Jane Smith", "25", "jane.smith@example.com"]}]}');
--
-- insert into blocks (block_type, created_at, updated_at, page_id, created_by_id) values ('JOURNAL', '2000-01-01', '2000-01-01', 1, 2);
-- insert into journal_block (id, journal_date, content) values (7, '2024-07-31', 'happy');
