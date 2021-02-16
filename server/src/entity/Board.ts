import { Entity, PrimaryGeneratedColumn, Column, CreateDateColumn, UpdateDateColumn, OneToOne, JoinColumn } from 'typeorm';
import { Category } from './Category';
import { Member } from './Member';

@Entity()
export class Board {
    @PrimaryGeneratedColumn()
    id!: number;

    @OneToOne(type => Member)
    @JoinColumn()
    writer!: Member;

    @CreateDateColumn({ type: 'datetime' })
    regDate!: Date;

    @UpdateDateColumn({ type: 'datetime' })
    editDate!: Date;

    @Column({ type: 'varchar', length: 5000 })
    content!: string;

    @Column({ type: 'json', nullable: true })
    files!: JSON;

    @Column({ default: 0 })
    favorite!: number;

    @OneToOne(type => Category)
    @JoinColumn()
    category!: Category;
}
